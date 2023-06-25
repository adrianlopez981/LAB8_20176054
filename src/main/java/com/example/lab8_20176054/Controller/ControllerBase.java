package com.example.lab8_20176054.Controller;


import com.example.lab8_20176054.Entity.evento;
import com.example.lab8_20176054.Repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController //me añade el @ResponseBody
@CrossOrigin // para evitar bloqueos
@RequestMapping(value = "/evento")
public class ControllerBase {

//    @Autowired
//    DaoEmpresa daoEmpresa;


    @Autowired
    EventoRepository eventoRepository;
//
//    @Autowired
//    SupplierDao supplierDao;


    @GetMapping({""})
    public List<evento> listarEventos() {

        return eventoRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<HashMap<String, Object>> buscarxId(@PathVariable(value = "id", required = false) String idParam) {

        HashMap<String, Object> responseMap = new HashMap<>();

        try {
            int id = Integer.parseInt(idParam);
            if (id > 0) {
                Optional<evento> opt = eventoRepository.findById(id);
                if (opt.isPresent()) {
                    evento evento = opt.get();
                    responseMap.put("evento", evento);
                    responseMap.put("resultado", "exitoso");

                    return ResponseEntity.ok(responseMap);
                }else {

                    responseMap.put("msg", "Evento no encontrado");
                    responseMap.put("resultado", "Falla");
                    return ResponseEntity.badRequest().body(responseMap);
                }


                }

        } catch (NumberFormatException e) {
            responseMap.put("msg", "el ID debe ser un número entero positivo");
            responseMap.put("resultado", "Falla");
            return ResponseEntity.badRequest().body(responseMap);
        }
        return ResponseEntity.ok(responseMap);
    }


    @PostMapping("")
    public ResponseEntity<HashMap<String, Object>> guardarEvento(
            @RequestBody evento evento,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> responseMap = new HashMap<>();


        eventoRepository.save(evento);

        if(fetchId){
            responseMap.put("id",evento.getId());
        }


        responseMap.put("estado", "creado");

        return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
    }




//    @GetMapping("/new")
//    public String nuevoProductoFrm(@ModelAttribute("product") Product product, Model model) {
//        model.addAttribute("listaCategorias", categoryDao.listarCategorias());
//        model.addAttribute("listaProveedores", supplierDao.listarProveedores());
//        return "product/form";
//    }
//
//    @PostMapping("/save")
//    public String guardarProducto(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult,
//                                  Model model, RedirectAttributes attr) {
//
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("listaCategorias", categoryDao.listarCategorias());
//            model.addAttribute("listaProveedores", supplierDao.listarProveedores());
//            return "product/form";
//        } else {
//            String msg = "Producto " + (product.getId() == 0 ? "creado" : "actualizado") + " exitosamente";
//            attr.addFlashAttribute("msg", msg);
//            productDao.guardarProducto(product);
//            return "redirect:/product";
//        }
//    }
//
//    @GetMapping("/edit")
//    public String editarTransportista(Model model, @RequestParam("id") int id) {
//
//        Product productBuscar = productDao.obtenerProductoPorId(id);
//
//        if (productBuscar != null) {
//            model.addAttribute("product", productBuscar);
//            model.addAttribute("listaCategorias", categoryDao.listarCategorias());
//            model.addAttribute("listaProveedores", supplierDao.listarProveedores());
//            return "product/form";
//        } else {
//            return "redirect:/product";
//        }
//    }
//
//    @GetMapping("/delete")
//    public String borrarTransportista(Model model, @RequestParam("id") int id, RedirectAttributes attr) {
//
//        Product productBuscar = productDao.obtenerProductoPorId(id);
//
//        if (productBuscar != null) {
//            productDao.borrarProducto(id);
//            attr.addFlashAttribute("msg", "Producto borrado exitosamente");
//        }
//        return "redirect:/product";
//
//    }
}
