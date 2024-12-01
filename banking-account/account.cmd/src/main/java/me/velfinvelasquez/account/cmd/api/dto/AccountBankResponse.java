package me.velfinvelasquez.account.cmd.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.velfinvelasquez.account.common.dto.BaseResponse;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountBankResponse extends BaseResponse {
    private String id;

    public AccountBankResponse(String message, String id) {
        super(message);
        this.id = id;
    }
}
