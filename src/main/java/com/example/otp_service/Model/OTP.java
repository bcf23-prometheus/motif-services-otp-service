package com.example.otp_service.Model;



import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name ="otp", schema = "public")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OTP {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column (nullable = false)
    private Date time;

    @Column (nullable = false)
    private String email;

    @Column (nullable = false)
    private String otp;
}
