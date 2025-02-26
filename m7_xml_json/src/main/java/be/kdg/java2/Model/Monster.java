package be.kdg.java2.Model;






import be.kdg.java2.parsing.LocalDateAdapter;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;
import java.util.Objects;

@XmlType(propOrder = {"name", "totalXp", "level", "type", "birthday", "battlesWon"})
public class Monster implements Comparable {
    private String name;
    private double totalXp;
    private int level;
    private MonsterType type;
    private LocalDate birthday;
    private int battlesWon;

    //Default Constructor
    public Monster() {
        this.name = "UnKnown";
        this.totalXp = 0.0;
        this.level = 0;
        this.type = MonsterType.neutral;
        this.birthday = LocalDate.of(2000,1,1) ;
        this.battlesWon = 0;
    }

    //Default Constructor
    //public Monster() {
    //    this.name = "Test";
    //    this.totalXp = 10.0;
    //    this.level = 2;
    //    this.type = MonsterType.neutral;
    //    this.birthday = LocalDate.of(2000,1,1) ;
    //    this.battlesWon = 10;
    //}

    // Constructor
    public Monster(String name, double totalXp, int level, MonsterType type, LocalDate birthday, int battlesWon) {
        setName(name);
        setTotalXp(totalXp);
        setLevel(level);
        setType(type);
        setBirthday(birthday);
        setBattlesWon(battlesWon);
    }

    //getters
    public String getName() {
        return name;
    }
    public double getTotalXp() {
        return totalXp;
    }
    public int getLevel() {
        return level;
    }
    public MonsterType getType() {
        return type;
    }
    public LocalDate getBirthday() {
        return birthday;
    }
    public int getBattlesWon() {
        return battlesWon;
    }

    //setters
    @XmlAttribute(name = "name")
    public void setName(String name) {
        if (name == null || name.equals("")){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        else {
            this.name = name;
        }
    }
    @XmlElement(name = "total-xp")
    public void setTotalXp(double totalXp) {
        if (totalXp < 0){
            throw new IllegalArgumentException("Total XP cannot be less then 0 ");
        }
        else {
            this.totalXp = totalXp;
        }
    }
    @XmlElement(name = "level")
    public void setLevel(int level) {
        if (level < 0){
            throw new IllegalArgumentException("Level has to be above (or equal to) 0 ");
        }
        else {
            this.level = level;
        }
    }
    @XmlElement(name = "type")
    public void setType(MonsterType type) {
        if (type == null){
            throw new IllegalArgumentException("Monster type cannot be nothing");
        }
        else {
            this.type = type;
        }
    }
    @XmlElement(name = "birthday")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public void setBirthday(LocalDate birthday) {
        if (birthday.isAfter(LocalDate.now().plusDays(1))){
            throw new IllegalArgumentException("Date of birth cannot be after today");
        }
        else {
            this.birthday = birthday;
        }
    }
    @XmlElement(name = "battles-won")
    public void setBattlesWon(int battlesWon) {
        if (battlesWon < 0){
            throw new IllegalArgumentException("Battles won cannot be less then 0");
        }
        else {
            this.battlesWon = battlesWon;
        }
    }

// checkes if object is unique (name)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monster monster = (Monster) o;
        return getName().equals(monster.getName());
    }

    // creates unique key (with name)
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Object o) {
        Monster monster = (Monster) o;
        return this.getName().compareTo(monster.getName());
    }

    @Override
    public String toString() {
        return String.format("%-35s has %-10f for a level of %-10d, type: %-22s,  born on monthly %10s",
                getName(),getTotalXp(),getLevel(),getType(),getBirthday());
    }

}
