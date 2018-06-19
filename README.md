JSend version 1.1.0
===================

Java JSend implementation for JSON serialization.
JSend is an easy to use library for creating JSON responses according to JSend specification.

Jackson library is required for JSON serialization and JUnit for testing it.
Maven tool is required for build. And Java 8 is required with this release.

You can read JSend specification at http://labs.omniti.com/labs/jsend

In src/test/java folder you can see a JUnit Test with two functions of how to use it.

Modernized to use latest and removed Custom Serializer which imposed validation rules.

Example of adding ResourceSupport to JSend:

  ```java
package com.somepackage;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import org.json.JsonStruct;
import org.springframework.hateoas.ResourceSupport;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CommonResponseResource extends ResourceSupport {

    @JsonProperty("content")
    private JsonStruct jsonStruct;

    @JsonCreator
    public CommonResponseResource(@JsonProperty("content") JsonStruct jsonStruct) {
        this.jsonStruct = jsonStruct;
    }
    
}


  ```
