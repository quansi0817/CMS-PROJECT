package ca.sheridancollege.SichaoQuan.beans;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Component
@NoArgsConstructor
@RequiredArgsConstructor
public class Users {
	private Long userId;
	@NonNull
	private String encryptedPassword;
	@NonNull
	private String email;
	@NonNull
	private Boolean enabled;
	
}


