package dev.joseluisgs.rest.responses.getbyid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true) // Ignorar propiedades desconocidas en el JSON
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGetById {

    @JsonProperty("data")
    private UserGetById data;

    @JsonProperty("support")
    private Support support;

    public UserGetById getData() {
        return data;
    }

    public Support getSupport() {
        return support;
    }
}