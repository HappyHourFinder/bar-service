package com.mathieuaime.hhf.core.bar.repository

import com.mathieuaime.hhf.core.bar.model.BarEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BarRepository : JpaRepository<BarEntity, String>