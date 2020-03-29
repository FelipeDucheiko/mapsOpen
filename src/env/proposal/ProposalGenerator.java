package proposal;

import cartago.*;

public class ProposalGenerator extends Artifact {
	void init(int initialValue) {
		defineObsProperty("count", initialValue);
	}

	@OPERATION
	void inc() {
		ObsProperty prop = getObsProperty("count");
		prop.updateValue(prop.intValue()+1);
		signal("tick");
	}
	
	@OPERATION
	void generateOffer(Object maxValue, Object maxDistance, Object locationParkedSpotX, Object locationParkedSpotY, Object targetLocationX, Object targetLocationY, OpFeedbackParam<Object> offer) {
		int maxValueInt = Integer.parseInt(maxValue.toString());
		int maxDistanceInt = Integer.parseInt(maxDistance.toString());
		double locationParkedSpotXDouble = Double.parseDouble(locationParkedSpotX.toString());
		double locationParkedSpotYDouble = Double.parseDouble(locationParkedSpotY.toString());
		double targetLocationXDouble = Double.parseDouble(targetLocationX.toString());
		double targetLocationYDouble = Double.parseDouble(targetLocationY.toString());
		
		double distance = calculateDistance(locationParkedSpotXDouble, locationParkedSpotYDouble, targetLocationXDouble, targetLocationYDouble);

		double o = maxValueInt - (distance/maxDistanceInt);

		offer.set(o);
	}

	private double calculateDistance(double locationParkedSpotXDouble, double locationParkedSpotYDouble, double targetLocationXDouble, double targetLocationYDouble) {
		return Math.sqrt(Math.pow(locationParkedSpotXDouble - targetLocationXDouble, 2) + Math.pow(locationParkedSpotYDouble - targetLocationYDouble, 2));
	}
	
	
}