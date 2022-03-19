package project.model;

public abstract class BaseModel implements Comparable<BaseModel> {

    public abstract Long getId();

    public abstract void setId(Long id);

    public abstract String toString();

    public abstract int compareTo(BaseModel o);

}
