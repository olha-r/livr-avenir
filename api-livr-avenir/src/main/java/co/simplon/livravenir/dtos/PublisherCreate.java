package co.simplon.livravenir.dtos;

public class PublisherCreate {

    private String name;

    public PublisherCreate() {
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @Override
    public String toString() {
	return String.format("{name=%s}", name);
    }

}
