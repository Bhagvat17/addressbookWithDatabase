import com.example.addressbookdb.PersonId
import com.example.addressbookdb.requests.*

fun getPersonCreateRequest(
    firstName: String = "BhagvatSinh",
    lastName: String = "Jadeja",
) =
    AddPersonRequest(
        firstName = firstName,
        lastName = lastName,
    )

fun getPersonUpdateRequest(
    personId: PersonId,
    firstName: String = "BhagvatSinh_UPDATED",
    lastName: String = "Jadeja_UPDATED",
    )
 =
    UpdatePersonRequest(
        personId= personId,
        firstName = firstName,
        lastName = lastName,
    )