package at.fhj.ima.forumengine.forumengine.repository

import at.fhj.ima.forumengine.forumengine.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
interface UserRepository : JpaRepository<User, Int> {

    @Query("FROM User WHERE username = :username")
    fun findByUsername(@Param("username") username: String): User

    @Transactional
    @Modifying
    @Query("UPDATE User SET isBanned = true WHERE userId = :userId")
    fun banById(@Param("userId") userId: Int)

    @Transactional
    @Modifying
    @Query("UPDATE User SET isBanned = false WHERE userId = :userId")
    fun unbanById(@Param("userId") userId: Int)

    @Transactional
    @Modifying
    @Query("UPDATE User SET firstname = :firstname, lastname = :lastname," +
            "password = :password, securityQuestion = :securityQuestion, securityQuestionAnswer = :securityQuestionAnswer WHERE userId = :userId")
    fun updateById(@Param("userId") userId: Int,
                   @Param("firstname") firstname: String,
                   @Param("lastname") lastname: String,
                   @Param("password") password: String,
                   @Param("securityQuestion") securityQuestion: String,
                   @Param("securityQuestionAnswer") securityQuestionAnswer: String)
}