package test.repairservice.repairservicev2.util;

import test.repairservice.repairservicev2.model.RepairRequestStatus;

public enum RequestStatus {
    ACCEPTED(1L,"Accepted"), IN_PROGRESS(2L,"In Progress"), COMPLETED(3L, "Completed");

    private Long id;
    private String title;

    RequestStatus(Long id, String title){
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

    public static RequestStatus identifyRequestStatus(String status){
        switch (status){
            case "Accepted":
                return ACCEPTED;

            case "In Progress":
                return IN_PROGRESS;

            case "Completed":
                return COMPLETED;

        }
     return ACCEPTED;
    }
}
