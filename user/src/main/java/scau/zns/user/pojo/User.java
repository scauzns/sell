package scau.zns.user.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class User {
    @Id
    private String id;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 性别：0 男 1 女
     */
    private Byte gender;

    /**
     * 登录密码
     */
    private String password;


    /**
     * 1 普通用户  2 管理员
     */
    @Column(name = "user_type")
    private Byte userType;


    /**
     *  用户头像
     */
    private String cover;

    /**
     * 0 有效  1冻结
     */
    private Byte status;

    @Column(name = "create_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    @Column(name = "update_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取用户昵称
     *
     * @return name - 用户昵称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户昵称
     *
     * @param name 用户昵称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取手机号
     *
     * @return phone - 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号
     *
     * @param phone 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取性别：0 男 1 女
     *
     * @return gender - 性别：0 男 1 女
     */
    public Byte getGender() {
        return gender;
    }

    /**
     * 设置性别：0 男 1 女
     *
     * @param gender 性别：0 男 1 女
     */
    public void setGender(Byte gender) {
        this.gender = gender;
    }

    /**
     * 获取登录密码
     *
     * @return password - 登录密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置登录密码
     *
     * @param password 登录密码
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * 获取1 普通用户  2 管理员
     *
     * @return user_type - 1 普通用户  2 管理员
     */
    public Byte getUserType() {
        return userType;
    }

    /**
     * 设置1 普通用户  2 管理员
     *
     * @param userType 1 普通用户  2 管理员
     */
    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    /**
     * 获取0 有效  1冻结
     *
     * @return status - 0 有效  1冻结
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置0 有效  1冻结
     *
     * @param status 0 有效  1冻结
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}