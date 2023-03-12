package test;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude={"firstname", "address", "birthday"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {

    private static final long serialVersionUID = 1L;

    @NonNull private String lastname;
    @NonNull private String firstname;

    private Date birthday;
    private String address;
    private String city;
    private String zip;

}
