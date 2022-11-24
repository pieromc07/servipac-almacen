package com.servipac.almacen.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@Builder
public class UpdateStatusRequest {

       @NotNull(message = "El estado es obligatorio")
       private Boolean status;

       @JsonCreator
       public UpdateStatusRequest(@JsonProperty("status") Boolean status) {
              this.status = status;
       }

}
