package hr.xmjosic.restfulwebservice.helloworld.model;

/**
 * View class for json serializer/deserializer
 */
public class View {
    /**
     * Properties to serialize when returning the list of all users.
     */
    public interface AllUsersList {}

    /**
     * Properties to deserialize when creating a new user.
     */
    public interface createUser {}
}
