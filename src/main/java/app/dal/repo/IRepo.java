package app.dal.repo;

public interface IRepo <ENTITY,ID>{
    boolean add(ENTITY entity);
}