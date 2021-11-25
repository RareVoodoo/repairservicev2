package test.repairservice.repairservicev2.util;

public enum UserAuthority {
    CLIENT(1L,"Client"), MANAGER(2L,"Manager"), MASTER(3L,"Master");

    private Long id;
    private String title;

    UserAuthority(Long id,String title){
        this.id = id;
        this.title = title;
    }

    public Long getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }
}
