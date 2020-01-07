abstract class Phone {
	private String branch;
	private String model;
	private String color;

	public Phone(String branch) {
		this.branch = branch;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String toString() {
		return this.branch + "{model=" + model
			+ ", color=" + color + "}";
	}
}


class IPhone extends Phone {
	public IPhone() {
		super("IPhone");
	}
}


class Oneplus extends Phone {
	public Oneplus() {
		super("Oneplus");
	}
}


abstract class PhoneFactory {
	public final Phone create(String model, String color) {
		Phone phone = create_phone();
		phone.setModel(model);
		phone.setColor(color);
		return phone;
	}

	public abstract Phone create_phone();
}


class IPhoneFactory extends PhoneFactory {
	public Phone create_phone() {
		return new IPhone();
	}
}


class OneplusFactory extends PhoneFactory {
	public Phone create_phone() {
		return new Oneplus();
	}
}


public class FactoryMethod {
	public static void main(String[] args) {
		System.out.println(new IPhoneFactory().create("IPhone", "black"));
		System.out.println(new OneplusFactory().create("Oneplus3T", "white"));
	}
}