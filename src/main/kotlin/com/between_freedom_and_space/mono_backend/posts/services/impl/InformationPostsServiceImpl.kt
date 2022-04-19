package com.between_freedom_and_space.mono_backend.posts.services.impl

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.entities.post.Post
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.InformationCommentsService
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.BaseCommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.InformationPostReactionsService
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.BasePostReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.InformationTagsService
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.BaseTagModel
import com.between_freedom_and_space.mono_backend.posts.repository.CommonPostRepository
import com.between_freedom_and_space.mono_backend.posts.services.InformationPostsService
import com.between_freedom_and_space.mono_backend.posts.services.models.BasePostModel
import com.between_freedom_and_space.mono_backend.posts.services.models.PostReactionsCountModel
import org.jetbrains.exposed.sql.transactions.transaction

class InformationPostsServiceImpl(
    private val repository: CommonPostRepository,
    private val mapper: ModelMapper<Post, BasePostModel>,
    private val commentsService: InformationCommentsService,
    private val tagsService: InformationTagsService,
    private val reactionsService: InformationPostReactionsService,
): InformationPostsService {

    override fun getAllPosts(pageNumber: Int, pageSize: Int): List<BasePostModel> {
        val posts = repository.getAllPosts(pageNumber, pageSize)
        return posts.map { mapper.map(it) }
    }

    override fun getPostById(postId: Long): BasePostModel {
        val post = repository.getPostById(postId)
        return mapper.map(post)
    }

    override fun getPostComments(postId: Long, pageNumber: Int, pageSize: Int): List<BaseCommentModel> {
        return commentsService.getCommentsWithPostId(postId, pageNumber, pageSize)
    }

    override fun getPostTags(postId: Long, pageNumber: Int, pageSize: Int): List<BaseTagModel> {
        return tagsService.getTagsWithPostId(postId, pageNumber, pageSize)
    }

    override fun getPostReactions(postId: Long, pageNumber: Int, pageSize: Int): List<BasePostReactionModel> {
        return reactionsService.getReactionsWithPostId(postId, pageNumber, pageSize)
    }

    override fun getPostReactionsCount(postId: Long): PostReactionsCountModel {
        val reactions = transaction {

        }
    }
}