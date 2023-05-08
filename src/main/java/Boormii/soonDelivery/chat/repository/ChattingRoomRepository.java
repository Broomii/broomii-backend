package Boormii.soonDelivery.chat.repository;

import Boormii.soonDelivery.chat.domain.ChattingRoom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ChattingRoomRepository {
    private final EntityManager em;
    public List<Object[]> getChattingList(String nickName) {
        String sql = "SELECT * FROM chatting_room where delivery_man = :nickName or order_man = :nickName";
        Query query = em.createNativeQuery(sql)
                .setParameter("nickName", nickName);
        return query.getResultList();
    }

    public Optional<ChattingRoom> findByMemberIdAndOrderId(Long memberId, Long orderId) {
        String sql = "select * from chatting_room where member_id = :memberId and orders_id = :orderId";
        Query query = em.createNativeQuery(sql, ChattingRoom.class)
                .setParameter("memberId", memberId)
                .setParameter("orderId", orderId);
        return query.getResultList().stream().findAny();
    }

    public void save(ChattingRoom chattingRoom) {
        em.persist(chattingRoom);
    }

    public ChattingRoom findById(Long roomId) {return em.find(ChattingRoom.class, roomId);}

}
