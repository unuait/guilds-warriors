package com.younait.guilds.domain.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name="guilds")
public class GuildEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "guild_id_identity")
    private int id;
    private String name;
    private String region;
}
