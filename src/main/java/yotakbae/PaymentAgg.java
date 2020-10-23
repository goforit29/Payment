package yotakbae;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="PaymentAgg_table")
public class PaymentAgg {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long memberId;
    private Long requestId;
    private String status;

    @PostPersist
    public void onPostPersist(){
        Paid paid = new Paid();
        BeanUtils.copyProperties(this, paid);

        try {
                Thread.sleep((long) (500 + Math.random() * 100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        paid.publishAfterCommit();


    }

    @PostUpdate
    public void onPostUpdate(){
        PayCanceled payCanceled = new PayCanceled();
        BeanUtils.copyProperties(this, payCanceled);
        payCanceled.publishAfterCommit();
        
        
        try {
                Thread.sleep((long) (450 + Math.random() * 100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
