import java.util.Date;
import java.util.List;

public class XManagerRolePojo {
    private String managerId;
    private String roleId;
    private String creOperId;
    private Date creOperDate;
    public String getManagerId() {
        return managerId;
    }
    public XManagerRolePojo setManagerId(String managerId) {
        this.managerId = managerId;
        return this;
    }

    public String getRoleId() {
        return roleId;
    }
    public XManagerRolePojo setRoleId(String roleId) {
        this.roleId = roleId;
        return this;
    }

    public String getCreOperId() {
        return creOperId;
    }
    public XManagerRolePojo setCreOperId(String creOperId) {
        this.creOperId = creOperId;
        return this;
    }

    public Date getCreOperDate() {
        return creOperDate;
    }
    public XManagerRolePojo setCreOperDate(Date creOperDate) {
        this.creOperDate = creOperDate;
        return this;
    }

}
