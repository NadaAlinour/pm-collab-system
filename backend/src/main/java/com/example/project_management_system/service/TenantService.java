package com.example.project_management_system.service;

import com.example.project_management_system.entity.Tenant;
import com.example.project_management_system.repository.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TenantService {
    private final TenantRepository tenantRepo;
    public TenantService(TenantRepository tenantRepo)
    {
        this.tenantRepo = tenantRepo;
    }

    public Tenant getTenant(Long id)
    {
         Optional<Tenant> optionalTenant = tenantRepo.findById(id);
         if (optionalTenant.isPresent())
         {
             return optionalTenant.get();
         } else {
             new RuntimeException("tenant not found");
             return null;
         }
    }
}
