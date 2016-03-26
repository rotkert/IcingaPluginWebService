package mgr.mkaminski;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ChecksWrapper {
	@XmlElement
	List<Check> checks;

	List<Check> getChecks() {
		return checks;
	}

	void setChecks(List<Check> checks) {
		this.checks = checks;
	}
}
