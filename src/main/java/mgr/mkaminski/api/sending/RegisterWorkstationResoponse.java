package mgr.mkaminski.api.sending;

import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RegisterWorkstationResoponse {
	private UUID token;
	private boolean countersRequested;
	
	@XmlElement
	public UUID getToken() {
		return token;
	}
	
	public void setToken(UUID token) {
		this.token = token;
	}
	
	@XmlElement
	public boolean isCountersRequested() {
		return countersRequested;
	}
	
	public void setCountersRequested(boolean countersRequested) {
		this.countersRequested = countersRequested;
	}
}
