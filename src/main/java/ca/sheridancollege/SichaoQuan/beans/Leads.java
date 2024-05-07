package ca.sheridancollege.SichaoQuan.beans;




import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@NoArgsConstructor
public class Leads {
    private Long leadsId;
    private Long customerId;
    private String projectName;
    private String products;
    private double unitPrice;
    private int quantity;
    private String currentStage;
    private Long currentStageId;
    public static final String[] STAGE = {"New Lead", "Proposal", "Won", "Lost"};

    public int getTotalAmount() {
        return (int) (unitPrice * quantity); 
    }
}