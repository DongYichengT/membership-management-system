package dongyicheng;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

// 积分日志实体类，用于映射数据库中的 points_log 表，记录用户积分的变动情况
@Entity
@Table(name = "points_log")
public class PointsLog {

    // 定义该实体的唯一标识字段，使用自增长策略生成唯一 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId; // 记录ID，用于唯一标识每一条积分变动记录

    // 用户名，不能为空，用于关联具体的用户
    @Column(nullable = false)
    private String username; // 用户名，记录该积分变动操作对应的用户

    // 操作类型，使用枚举类型来表示，并且存储为字符串形式，不能为空
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ActionType action; // 操作类型，表明该记录是积分获取还是积分消费操作

    // 创建时间字段，名为 created_time，允许为空，且在更新时不会被修改，存储为时间戳格式
    @Column(name = "created_time", nullable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime; // 创建时间，记录该积分变动操作发生的具体时间

    // 积分数，不能为空，记录该次操作变动的积分数量
    @Column(nullable = false)
    private int point; // 积分数，记录该次积分变动的具体数值

    // 操作原因，字段长度最大为 128，用于记录积分变动的原因
    @Column(length = 128)
    private String reason; // 操作原因，详细说明该次积分变动的具体缘由，例如“购买商品获得积分”

    // Getters and Setters 方法，用于获取和设置各个字段的值
    // 获取记录 ID
    public Long getRecordId() {
        return recordId;
    }

    // 设置记录 ID
    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    // 获取用户名
    public String getUsername() {
        return username;
    }

    // 设置用户名
    public void setUsername(String username) {
        this.username = username;
    }

    // 获取操作类型
    public ActionType getAction() {
        return action;
    }

    // 设置操作类型
    public void setAction(ActionType action) {
        this.action = action;
    }

    // 获取创建时间
    public Date getCreatedTime() {
        return createdTime;
    }

    // 设置创建时间
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    // 获取积分数
    public int getPoint() {
        return point;
    }

    // 设置积分数
    public void setPoint(int point) {
        this.point = point;
    }

    // 获取操作原因
    public String getReason() {
        return reason;
    }

    // 设置操作原因
    public void setReason(String reason) {
        this.reason = reason;
    }
}

// 操作类型枚举，定义了积分变动的两种操作类型：获取积分和消费积分
enum ActionType {
    // 表示获取积分的操作
    earn,
    // 表示消费积分的操作
    spend
}