package Boormii.soonDelivery.chat.repository;

import Boormii.soonDelivery.chat.domain.ChattingMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChattingMessageRepository extends JpaRepository<ChattingMessage, Long> {
}
