import com.addressbook.commands.Command
import com.addressbook.commands.toPhoneNumber
import com.addressbook.storages.GroupDB
import com.example.addressbookdb.Group
import com.addressbook.requests.AddGroupRequest
import com.addressbook.requests.UpdateGroupRequest
import com.addressbook.storages.PhoneNumberDB
import com.example.addressbookdb.PhoneNumber
import com.example.addressbookdb.requests.UpdatePhoneNumberRequest
import java.util.*

fun AddGroupRequest.toGroup() =
    Group(
        groupId = UUID.randomUUID(),
        personId = this@toGroup.personId,
        groupName = this@toGroup.groupName,
    )

fun UpdateGroupRequest.toGroup() =
    Group(
        groupId = this@toGroup.groupId,
        personId = this@toGroup.personId,
        groupName = this@toGroup.groupName,
    )

class AddGroupCommand(
    private val storage: GroupDB,
    private val request: AddGroupRequest
): Command {
    override fun execute(): Group {
        val group = request.toGroup()


        val groupDetail = storage.addGroup(group)

        return Group(
            groupId= groupDetail.groupId,
            personId = groupDetail.personId,
            groupName = groupDetail.groupName,
        )
    }
}

class UpdatePhoneNumberCommand(
    private val storage: PhoneNumberDB,
    private val request: UpdatePhoneNumberRequest
) : Command {
    override fun execute(): PhoneNumber {
        val phoneNumber = request.toPhoneNumber()
        return storage.updatePhoneNumber(phoneNumber)
    }
}