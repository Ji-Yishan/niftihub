package com.example.niftihub.pojo.data.before;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Isabella
 * @create: 2023-09-23 18:03
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DecisionDO {
    private int deci_id;
    private String reason;
    private int percentage;
    private int d_value;
    private int id;

    public DecisionDO(int deciId, String reason, int percentage, int dValue) {
        this.deci_id = deciId;
        this.reason = reason;
        this.percentage = percentage;
        this.d_value = dValue;
    }
    public DecisionDO(int deciId, String reason, int percentage) {
        this.deci_id = deciId;
        this.reason = reason;
        this.percentage = percentage;
    }
}
