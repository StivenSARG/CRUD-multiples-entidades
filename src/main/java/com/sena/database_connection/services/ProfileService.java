package com.sena.database_connection.services;

import java.util.List;
import java.util.Optional;

import com.sena.database_connection.entities.Profile;
import com.sena.database_connection.repositories.ProfileRepository;

public class ProfileService {

    private ProfileRepository repository;

    // Constructor con inyeccion de dependecias
    public ProfileService(ProfileRepository repository) {
        this.repository = repository;
    }

    // Método para obtener todos los profile registrados
    public List<Profile> obtenerTodos() {

        // Llama al método findAll de JPARepository para obtener todos los
        // registros de la tabla profile.
        return this.repository.findAll();
    }

    // Método para obtener un profile por id
    public Optional<Profile> porId(Long id) {
        // Llama al método findById de JPARepository para buscar un profile
        // utilizando su id.
        //
        // El resultado se encapsula en Optional<Profile> porque puede ocurrir
        // que no exista un Role con ese id.
        return this.repository.findById(id);
    }

    // Método para crear un profile
    // Recibe una instancia de profile con el id en null
    public Profile crear(Profile profile) {

        // Llama al método save de JPARepository para guardar el profile
        // en la base de datos.
        return this.repository.save(profile);
    }

    // Método para actualizar un profile existente
    // Recibe una instancia de profile con el id y los nuevos datos
    public Profile actualizar(Profile profile) {

        // Se busca el profile por id para validar que exista
        Optional<Profile> profileFound = this.porId(profile.getId());

        // Si el role no existe, retorna null
        if (profileFound.isEmpty()) {
            return null;
        }

        // Si existe, save actualizará automáticamente el registro
        // en la base de datos.
        return this.repository.save(profile);
    }

    // Método para eliminar un profile por id
    public Profile eliminar(Long id) {

        // Se busca el profile para validar que exista antes de eliminarlo
        Optional<Profile> profileFound = this.porId(id);

        // Si el profile no existe, retorna null 
        if (profileFound.isEmpty()) {
            return null;
        }

        // get() obtiene el objeto profile contenido dentro del Optional
        // y luego se elimina de la base de datos.
        this.repository.delete(profileFound.get());

      // Retorna el profile eliminado
        return profileFound.get();
    }

}
