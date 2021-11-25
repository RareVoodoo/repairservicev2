package test.repairservice.repairservicev2.util;

import test.repairservice.repairservicev2.model.Device;

public enum MasterDevice {
    PHONE(1L,"Phone"), PC(2L,"PC"), LAPTOP(3L, "Lapop"), TABLET(4L,"Tablet");

    private Long id;
    private String title;

    MasterDevice(Long id, String title){
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static Device identifyDevice(String title){
        switch (title){
            case "Phone":
                return Device.builder()
                        .id(PHONE.id)
                        .title(PHONE.title)
                        .build();

            case "PC":
                return Device.builder()
                        .id(PC.id)
                        .title(PC.title)
                        .build();

            case "Lapop":
                return Device.builder()
                        .id(LAPTOP.id)
                        .title(LAPTOP.title)
                        .build();

            case "Tablet":
                return Device.builder()
                        .id(TABLET.id)
                        .title(TABLET.title)
                        .build();

        }
     return Device.builder()
             .id(PHONE.id)
             .title(PHONE.title)
             .build();
    }
}
