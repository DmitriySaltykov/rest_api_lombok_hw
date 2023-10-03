package api_lombok_test.models;

import lombok.Data;

@Data
public class CreateUserResponseModel {
    String name, job, id, CreatedAt;
}
