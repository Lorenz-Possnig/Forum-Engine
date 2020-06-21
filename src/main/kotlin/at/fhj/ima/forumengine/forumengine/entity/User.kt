package at.fhj.ima.forumengine.forumengine.entity

import javax.persistence.*
import javax.validation.constraints.Size

enum class UserRole {
    ROLE_USER,
    ROLE_MOD,
    ROLE_ADMIN
}

@Entity
@Table(uniqueConstraints = [UniqueConstraint(name = "uk_username", columnNames = arrayOf("username"))])
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
        var mail: String? = null,
        @Enumerated(EnumType.STRING)
        var role: UserRole = UserRole.ROLE_USER,
        @OneToMany(mappedBy = "createdBy", orphanRemoval = true, cascade = [CascadeType.ALL])
        var forums: MutableList<Forum> = mutableListOf(),
        @OneToMany(mappedBy = "createdBy", orphanRemoval = true, cascade = [CascadeType.ALL])
        var questions: MutableList<Question> = mutableListOf(),
        @OneToMany(mappedBy = "createdBy", orphanRemoval = true, cascade = [CascadeType.ALL])
        var answers: MutableList<Answer> = mutableListOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (userId != other.userId) return false
        if (username != other.username) return false
        if (password != other.password) return false
        if (firstname != other.firstname) return false
        if (lastname != other.lastname) return false
        if (role != other.role) return false
        if (forums != other.forums) return false
        if (questions != other.questions) return false
        if (answers != other.answers) return false

        return true
    }

    override fun hashCode(): Int {
        var result = userId?.hashCode() ?: 0
        result = 31 * result + username.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + firstname.hashCode()
        result = 31 * result + lastname.hashCode()
        result = 31 * result + role.hashCode()
        result = 31 * result + forums.hashCode()
        result = 31 * result + questions.hashCode()
        result = 31 * result + answers.hashCode()
        return result
    }

    override fun toString(): String {
        return "User(userId=$userId, username='$username', password='$password', firstname='$firstname', lastname='$lastname', role=$role, forums=$forums, questions=$questions, answers=$answers)"
    }
}