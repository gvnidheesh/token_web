package in.gov.kerala.ehealth.token.dto;

public class PersonDTO {
    private Long id;
    private String name;

    public PersonDTO() {
        // Default constructor for Jackson/serialization
    }

    public PersonDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
