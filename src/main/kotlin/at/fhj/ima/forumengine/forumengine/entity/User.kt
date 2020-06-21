package at.fhj.ima.forumengine.forumengine.entity

import org.springframework.beans.factory.annotation.Value
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

enum class UserRole {
    ROLE_USER,
    ROLE_MOD,
    ROLE_ADMIN
}

@Entity
@Table(uniqueConstraints = [UniqueConstraint(name = "uk_username", columnNames = arrayOf("username")),
                            UniqueConstraint(name = "uk_mail", columnNames = ["mail"])])
class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var userId: Int? = null,
        @Column(name="username")
        @field:Size(min = 3, max = 26)
        var username: String? = null,
        var password: String? = null,
        var firstname: String? = null,
        var lastname: String? = null,
        @Column(name="mail")
        @field:Size(min=3,max = 80)
        var mail: String? = null,
        @Enumerated(EnumType.STRING)
        var role: UserRole = UserRole.ROLE_USER,
        @OneToMany(mappedBy = "createdBy", orphanRemoval = true, cascade = [CascadeType.ALL])
        var forums: MutableList<Forum> = mutableListOf(),
        @OneToMany(mappedBy = "createdBy", orphanRemoval = true, cascade = [CascadeType.ALL])
        var questions: MutableList<Question> = mutableListOf(),
        @OneToMany(mappedBy = "createdBy", orphanRemoval = true, cascade = [CascadeType.ALL])
        var answers: MutableList<Answer> = mutableListOf(),
        @Column
        @field:NotNull
        var isBanned: Boolean = false,
        @OneToOne(mappedBy = "user")
        var profilePicture: PersistedImage? = null,
        var securityQuestion: String? = null,
        var securityQuestionAnswer: String? = null
) {
    fun getIsBanned(): Boolean = isBanned
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (userId != other.userId) return false
        if (username != other.username) return false
        if (password != other.password) return false
        if (firstname != other.firstname) return false
        if (lastname != other.lastname) return false
        if (mail != other.mail) return false
        if (role != other.role) return false
        if (forums != other.forums) return false
        if (questions != other.questions) return false
        if (answers != other.answers) return false
        if (isBanned != other.isBanned) return false
        if (profilePicture != other.profilePicture) return false
        if (securityQuestion != other.securityQuestion) return false
        if (securityQuestionAnswer != other.securityQuestionAnswer) return false

        return true
    }

    override fun hashCode(): Int {
        var result = userId ?: 0
        result = 31 * result + (username?.hashCode() ?: 0)
        result = 31 * result + (password?.hashCode() ?: 0)
        result = 31 * result + (firstname?.hashCode() ?: 0)
        result = 31 * result + (lastname?.hashCode() ?: 0)
        result = 31 * result + (mail?.hashCode() ?: 0)
        result = 31 * result + role.hashCode()
        result = 31 * result + forums.hashCode()
        result = 31 * result + questions.hashCode()
        result = 31 * result + answers.hashCode()
        result = 31 * result + isBanned.hashCode()
        result = 31 * result + (profilePicture?.hashCode() ?: 0)
        result = 31 * result + (securityQuestion?.hashCode() ?: 0)
        result = 31 * result + (securityQuestionAnswer?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "User(userId=$userId, username=$username, password=$password, firstname=$firstname, lastname=$lastname, mail=$mail, role=$role, forums=$forums, questions=$questions, answers=$answers, isBanned=$isBanned, profilePicture=${profilePicture?.id?:""}, securityQuestion=$securityQuestion, securityQuestionAnswer=$securityQuestionAnswer)"
    }


}