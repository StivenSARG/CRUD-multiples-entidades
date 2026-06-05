package com.sena.database_connection.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sena.database_connection.dtos.Post;
import com.sena.database_connection.repositories.PostRepository;

@Service
public class PostService {

    private PostRepository repository;

    // Constructor con inyeccion de dependecias
    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    // Método para obtener todos los post registrados
    public List<Post> obtenerTodos() {

        // Llama al método findAll de JPARepository para obtener todos los
        // registros de la tabla post.
        return this.repository.findAll();
    }

    // Método para obtener un post por id
    public Optional<Post> porId(Long id) {
        // Llama al método findById de JPARepository para buscar un Role
        // utilizando su id.
        //
        // El resultado se encapsula en Optional<Role> porque puede ocurrir
        // que no exista un Role con ese id.
        return this.repository.findById(id);
    }

    // Método para crear un post
    // Recibe una instancia de post con el id en null
    public Post crear(Post post) {

        // Llama al método save de JPARepository para guardar el post
        // en la base de datos.
        return this.repository.save(post);
    }

    // Método para actualizar un post existente
    // Recibe una instancia de post con el id y los nuevos datos
    public Post actualizar(Post post) {

        // Se busca el post por id para validar que exista
        Optional<Post> postFound = this.porId(post.getId());

        // Si el post no existe, retorna null
        if (postFound.isEmpty()) {
            return null;
        }

        // Si existe, save actualizará automáticamente el registro
        // en la base de datos.
        return this.repository.save(post);
    }

    // Método para eliminar un post por id
    public Post eliminar(Long id) {

        // Se busca el post para validar que exista antes de eliminarlo
        Optional<Post> postFound = this.porId(id);

        // Si el post no existe, retorna null
        if (postFound.isEmpty()) {
            return null;
        }

        // get() obtiene el objeto post contenido dentro del Optional
        // y luego se elimina de la base de datos.
        this.repository.delete(postFound.get());

        // Retorna el role eliminado
        return postFound.get();

    }
    
}
