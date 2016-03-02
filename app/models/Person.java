package models;

import com.avaje.ebean.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by User on 2/9/2016.
 */
@Entity
public class Person extends Model {
    public static Finder<String, Person> finder = new Finder<>(Person.class);

    @Id
    public Integer id;

    public String name;
    public String lastName;
    public String company;
    public String phone;
    public String mail;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    public List<File> files;

    public Person(){

    }

    public Person(String name, String lastName, String company, String phone, String mail, List<File> files) {
        this.name = name;
        this.lastName = lastName;
        this.company = company;
        this.phone = phone;
        this.mail = mail;
        this.files = files;
    }

        /* ------------------- save person ------------------ */


    public static void savePerson(String name, String lastName, String company, String phone, String mail) {

        Person person = new Person();
        person.name = name;
        person.lastName = lastName;
        person.company = company;
        person.phone = phone;
        person.mail = mail;
        person.save();

    }

        /* ------------------- update person ------------------ */


    public static void updatePerson(String name, String lastName, String company, String phone, String mail, Integer personId) {

        Person person = findPersonById(personId);
        person.name = name;
        person.lastName = lastName;
        person.company = company;
        person.phone = phone;
        person.mail = mail;

        person.update();
    }

        /* ------------------- delete person ------------------ */

    public static void deletePerson(Integer personId){
        Person person = findPersonById(personId);
        person.delete();
    }


        /* ------------------- get all persons ------------------ */

    public static List<Person> getAllPersons(){
        Model.Finder<String, Person> finder = new Model.Finder<>(Person.class);
        List<Person> persons = finder.all();
        return persons;
    }

     /* ------------------- get number of persons in database ------------------ */

    public static Integer getAllPersonsSize(){
        Integer numberOfPersons = finder.all().size();
        return numberOfPersons;
    }

        /* ------------------- finds person by id ------------------ */

    public static Person findPersonById (Integer id){
        Person person = finder.where().eq("id", id).findUnique();
        return person;
    }


     /* ------------------- person file------------------ */

    public static void savePersonFile(){

    }

}
