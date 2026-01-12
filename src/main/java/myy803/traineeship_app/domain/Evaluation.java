package myy803.traineeship_app.domain;

import jakarta.persistence.*;

@Entity
@Table(name="evaluations")
public class Evaluation {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
    @Column(name="evaluation_type")
    private EvaluationType evaluationType;
	
	@Column(name="motivation")
	private
	int motivation;
	
	@Column(name="efficiency")
	private
	int efficiency;
	
	@Column(name="effectiveness")
	private
	int effectiveness;

	@Column(name="facilities")
	private Integer facilities;

	@Column(name="guidance")
	private Integer guidance;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EvaluationType getEvaluationType() {
		return evaluationType;
	}

	public void setEvaluationType(EvaluationType evaluationType) {
		this.evaluationType = evaluationType;
	}

	public int getMotivation() {
		return motivation;
	}

	public void setMotivation(int motivation) {
		this.motivation = motivation;
	}

	public int getEfficiency() {
		return efficiency;
	}

	public void setEfficiency(int efficiency) {
		this.efficiency = efficiency;
	}

	public int getEffectiveness() {
		return effectiveness;
	}

	public void setEffectiveness(int effectiveness) {
		this.effectiveness = effectiveness;
	}


	public Integer getFacilities() {
		return facilities;
	}

	public void setFacilities(Integer facilities) {
		this.facilities = facilities;
	}

	public Integer getGuidance() {
		return guidance;
	}

	public void setGuidance(Integer guidance) {
		this.guidance = guidance;
	}
}
