package com.between_freedom_and_space.mono_backend.auth.service.impl

import com.between_freedom_and_space.mono_backend.auth.components.TokenProducer
import com.between_freedom_and_space.mono_backend.auth.components.TokenProducer.ProducerResult
import com.between_freedom_and_space.mono_backend.auth.components.TokenVerifier
import com.between_freedom_and_space.mono_backend.auth.components.UserPasswordEncryptor
import com.between_freedom_and_space.mono_backend.auth.components.exceptions.AuthenticateException
import com.between_freedom_and_space.mono_backend.auth.components.exceptions.InvalidTokenException
import com.between_freedom_and_space.mono_backend.auth.components.models.TokenVerifyResult
import com.between_freedom_and_space.mono_backend.auth.security.PasswordCipher
import com.between_freedom_and_space.mono_backend.auth.service.TokenAuthService
import com.between_freedom_and_space.mono_backend.profiles.repository.CommonProfilesRepository
import com.between_freedom_and_space.mono_backend.profiles.services.exceptions.ProfileNotFoundException

class JWTTokenAuthService(
    private val tokenProducer: TokenProducer,
    private val tokenVerifier: TokenVerifier,
    private val userPasswordEncryptor: UserPasswordEncryptor,
    private val profileRepository: CommonProfilesRepository,
): TokenAuthService {

    override fun refreshAccessToken(refreshToken: String): String {
        val verifyResult = tokenVerifier.verifyRefreshToken(refreshToken)
        return when (verifyResult) {
            is TokenVerifyResult.Valid ->
                tokenProducer.produceAccessToken(verifyResult.decodedToken)
            is TokenVerifyResult.Invalid ->
                throw InvalidTokenException("Refresh token is invalid")
            is TokenVerifyResult.Expired ->
                throw InvalidTokenException("Refresh token is expired")
        }
    }

    override fun verifyAccessToken(token: String): TokenVerifyResult {
        return tokenVerifier.verifyAccessToken(token)
    }

    override fun verifyRefreshToken(token: String): TokenVerifyResult {
        return tokenVerifier.verifyRefreshToken(token)
    }

    override fun authenticateUser(nickname: String, passwordEncoded: String): ProducerResult {
        val user = profileRepository.getProfileByNickname(nickname)
            ?: throw AuthenticateException("User with nickname: $nickname not found")
        val encryptedPassword = userPasswordEncryptor.encryptUserPassword(
            user.id.value, user.nickName, passwordEncoded
        )

        if (user.passwordEncrypted != encryptedPassword) {
            throw AuthenticateException("Invalid user password")
        }

        return tokenProducer.produceTokens(user.id.value, nickname)
    }
}