package com.z.model;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor // <--- THIS is it
@Document(collection = "todo", schemaVersion= "1.0")
public class Todo {
    private String username;
    private int state;
    private String body;
    @Id
    private String id;
}
