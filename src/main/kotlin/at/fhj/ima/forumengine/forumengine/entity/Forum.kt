package at.fhj.ima.forumengine.forumengine.entity

import java.io.Serializable
import javax.persistence.*

@Entity(name="Forum")
@Table()
class Forum(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var forumId: Int? = null,
        @Column
        var name: String = "",
        var description: String? = null,
        @OneToMany(mappedBy = "forum")
        var questions: MutableList<Question>
) {

}