package ca.sheridancollege.SichaoQuan.beans;




import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Component
@NoArgsConstructor
public class Customer {
	private Long customerId;
	private String name;
	private String phone;
	private String email;
    private Long userId;
  

}
