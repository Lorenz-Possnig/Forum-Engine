package at.fhj.ima.forumengine.forumengine.repository

import at.fhj.ima.forumengine.forumengine.entity.PersistedImage
import at.fhj.ima.forumengine.forumengine.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
interface ImageRepository : JpaRepository<PersistedImage, Int> {

    @Query("FROM PersistedImage WHERE user = :user")
    fun findByUser(@Param("user") user: User): PersistedImage?

    @Transactional
    @Modifying
    @Query("UPDATE PersistedImage SET bytes = :bytes WHERE user = :user")
    fun updateByUser(@Param("bytes") bytes: ByteArray, @Param("user") user: User)
}