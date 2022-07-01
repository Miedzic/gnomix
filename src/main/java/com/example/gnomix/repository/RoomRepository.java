package com.example.gnomix.repository;

import com.example.gnomix.domain.dao.BedType;
import com.example.gnomix.domain.dao.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Long> {


}
