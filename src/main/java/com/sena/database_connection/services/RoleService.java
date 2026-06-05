package com.sena.database_connection.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sena.database_connection.entities.Role;
import com.sena.database_connection.repositories.RoleRepository;

@Service
public class RoleService {

    private RoleRepository repository;

    // Constructor con inyeccion de dependecias
    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    // Método para obtener todos los roles registrados
    public List<Role> obtenerTodos() {

        // Llama al método findAll de JPARepository para obtener todos los
        // registros de la tabla role.
        return this.repository.findAll();
    }

    // Método para obtener un role por id
    public Optional<Role> porId(Long id) {
        // Llama al método findById de JPARepository para buscar un Role
        // utilizando su id.
        //
        // El resultado se encapsula en Optional<Role> porque puede ocurrir
        // que no exista un Role con ese id.
        return this.repository.findById(id);
    }

    // Método para crear un Role
    // Recibe una instancia de Role con el id en null
    public Role crear(Role role) {

        // Llama al método save de JPARepository para guardar el Role
        // en la base de datos.
        return this.repository.save(role);
    }

    // Método para actualizar un Role existente
    // Recibe una instancia de Role con el id y los nuevos datos
    public Role actualizar(Role role) {

        // Se busca el role por id para validar que exista
        Optional<Role> roleFound = this.porId(role.getId());

        // Si el role no existe, retorna null
        if (roleFound.isEmpty()) {
            return null;
        }

        // Si existe, save actualizará automáticamente el registro
        // en la base de datos.
        return this.repository.save(role);
    }

    // Método para eliminar un role por id
    public Role eliminar(Long id) {

        // Se busca el role para validar que exista antes de eliminarlo
        Optional<Role> roleFound = this.porId(id);

        // Si el role no existe, retorna null
        if (roleFound.isEmpty()) {
            return null;
        }

        // get() obtiene el objeto role contenido dentro del Optional
        // y luego se elimina de la base de datos.
        this.repository.delete(roleFound.get());

        // Retorna el role eliminado
        return roleFound.get();

    }
 

}
