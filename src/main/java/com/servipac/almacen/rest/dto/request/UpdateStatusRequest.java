package com.servipac.almacen.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@Builder
@NoArgsConstructor
public class UpdateStatusRequest {

       @NotNull(message = "El estado es obligatorio")
       private Boolean status;

       @JsonCreator
       public UpdateStatusRequest(@JsonProperty("status") Boolean status) {
              this.status = status;
       }

}
