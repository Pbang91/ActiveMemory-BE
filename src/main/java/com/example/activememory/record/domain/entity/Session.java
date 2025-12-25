package com.example.activememory.record.domain.entity;

import com.example.activememory.account.domain.vo.UserId;
import com.example.activememory.inventory.domain.vo.MyGymId;
import com.example.activememory.planning.domain.vo.RoutineId;
import com.example.activememory.record.domain.vo.SessionId;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "my_gym_id", nullable = false)
    private MyGymId myGymId;

    @Column(name = "user_id", nullable = false)
    private UserId userId;

    @Column(name = "routine_id")
    private RoutineId routineId;

    @OneToMany(mappedBy = "session")
    private List<WorkoutLog> workoutLogs;

    public SessionId getSessionId() {
        return SessionId.of(id);
    }
}
