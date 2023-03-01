package Boormii.soonDelivery.chat.repository;

import Boormii.soonDelivery.chat.domain.ChattingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, Long> {
}
