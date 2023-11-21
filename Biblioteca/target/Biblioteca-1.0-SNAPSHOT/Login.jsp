<%@page import="com.mycompany.biblioteca.Libro"%>
<%@page import="com.mycompany.biblioteca.Lista"%>
<%@page import="com.mycompany.biblioteca.Serializacion"%>
<!DOCTYPE html>

<% 
    Lista listaLibro = new Lista();
    
     ServletContext context = getServletContext();
     String terminoBusqueda = request.getParameter("search");
     
      listaLibro = Serializacion.leerArchivo(context);
      
      boolean verificar = listaLibro.verificar();
      
      
       Lista libros = new Lista ();
       libros=Serializacion.leerArchivo(context);
     String tablaHTML = Serializacion.listarLibros(terminoBusqueda, context, request);
     
     Lista librosPrest = new Lista ();
     
     librosPrest = Serializacion.leerPrestamo(context);
     boolean bandera = librosPrest.verificar();
     

     String prestamoHTML = Serializacion.listarPrestamos(terminoBusqueda, context, request);    
     

%>
     
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Freelancer - Start Bootstrap Theme</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link rel="stylesheet" href="templates/style.css">
    </head>
    

    <body id="page-top">
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg bg-secondary text-uppercase fixed-top" id="mainNav">
            <div class="container">
                <a class="navbar-brand" href="Login.jsp">B.U</a>
                <button class="navbar-toggler text-uppercase font-weight-bold bg-primary text-white rounded" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    Menu
                    <i class="fas fa-bars"></i>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="#portfolio">Libros disponibles</a></li>
                        <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="#about">Busca un libro</a></li>
                        <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="#contact">Agrega un libro</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- Masthead-->
        <header class="masthead bg-primary text-white text-center"style="background-image: linear-gradient(120deg, #a1c4fd 0%, #c2e9fb 100%);">
            
            <div class="container d-flex align-items-center flex-column">
                <!-- Masthead Avatar Image-->
                 <img src="https://cdn-icons-png.flaticon.com/128/5442/5442126.png"width="10%"
                                 alt="login form" class="img-fluid" style="border-radius: 1rem 0 0 1rem;" />
                <!-- Masthead Heading-->
                <h1 class="masthead-heading text-uppercase mb-0">Biblioteca Digital</h1>
                <!-- Icon Divider-->
                <div class="divider-custom divider-light">
                    <div class="divider-custom-line"></div>
                    <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                    <div class="divider-custom-line"></div>
                </div>
                <!-- Masthead Subheading-->
                <p class="masthead-subheading font-weight-light mb-0">Somos el cerebro de la U.Mariana</p>
            </div>
        </header>
        <!-- Portfolio Section-->
        <section class="page-section portfolio" id="portfolio">
            <div class="container">
                <!-- Portfolio Section Heading-->
                <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">Libros Disponibles</h2>
                <!-- Icon Divider-->
                <div class="divider-custom">
                    <div class="divider-custom-line"></div>
                    <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                    <div class="divider-custom-line"></div>
                </div>
            </div>
        </div>
        <html>
             <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-4">
    
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    </head>
    <body>     
        <center>
    <table border="1">

        <div class="container p-8"> <!-- clase contenedora -->
            </div>  
            <div class="col-md-8">
                    <table class="table table-bordered">
                    <thead>
                        <tr>

                            <th>Titulo</th>
                            <th>Autor</th>
                            <th>AÒo de publicacion</th>
                            <th>Portada</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                    <div>
                          <%
                            //Condicional if para saber si existen tareas en el archivo
                            if (!verificar || (terminoBusqueda == null && !verificar)) {
                        %>
                        <tr>
                            <td colspan='6' align='center' valign='middle'>No se han registrado Libro</td>
                        </tr>
                        <%
                            }
                        %>
                            
                        <%= tablaHTML%>
                            
                            </div>
                        </tr>
                    </tbody> 
                </table>
                </div>
               </div>   
            </div>    
        </div>
        
        
        <div class="container">
            
                <!-- Libros prestados-->
                <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">Libros prestados</h2>
                <!-- Icon Divider-->
                <div class="divider-custom">
                    <div class="divider-custom-line"></div>
                    <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                    <div class="divider-custom-line"></div>
                </div>
            </div>
         <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-4">
    
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    </head>
    <body>     
        <center>
    <table border="1">

        <div class="container p-8"> <!-- clase contenedora -->
            </div>  
            <div class="col-md-8">
                    <table class="table table-bordered">
                    <thead>
                        <tr>

                            <th>Titulo</th>
                            <th>Autor</th>
                            <th>AÒo de publicacion</th>
                            <th>Portada</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                    <div>
                          <%
                            //Condicional if para saber si existen tareas en el archivo
                            if (!bandera  || (terminoBusqueda == null && !bandera)) {
                        %>
                        <tr>
                            <td colspan='6' align='center' valign='middle'>No se han registrado Libro</td>
                        </tr>
                        <%
                            }
                        %>
                            
                        <%= prestamoHTML%>
                            
                            </div>
                        </tr>
                    </tbody> 
                </table>
                </div>
               </div>   
            </div>    
        </div>
        
       
                        
                        
        <!-- About Section-->
        <section class="page-section bg-primary text-white mb-0" id="about"style="background-image: linear-gradient(120deg, #a1c4fd 0%, #c2e9fb 100%);">
            <div class="container">
                <!-- About Section Heading-->
                <h2 class="page-section-heading text-center text-uppercase text-white">Busca un libro</h2>
                <!-- Icon Divider-->
                <div class="divider-custom divider-light">
                    <div class="divider-custom-line"></div>
                    <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                    <div class="divider-custom-line"></div>
                </div>
                <!-- About Section Button-->
                <div class="divider-custom divider-light">
                    
                    <!-- formulario de buscar libros-->
                <form action="SvBuscar" method="GET" class="d-flex" >
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="tiauto" style="max-width: 300px;">

                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
                    
                </div>
            </div>
        </section>
        
        <!-- Contact Section-->
        <section class="page-section" id="contact">
            <div class="container">
                
                
                <!-- Contact Section Heading-->
                <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">Agrega un libro</h2>
                <!-- Icon Divider-->
                <div class="divider-custom">
                    <div class="divider-custom-line"></div>
                    <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                    <div class="divider-custom-line"></div>
                </div>
                <!-- Contact Section Form-->
                <div class="row justify-content-center">
                    <div class="col-lg-8 col-xl-7">
                        <!-- * * * * * * * * * * * * * * *-->
                        <!-- * * SB Forms Contact Form * *-->
                        <!-- * * * * * * * * * * * * * * *-->
                        <!-- This form is pre-integrated with SB Forms.-->
                        <!-- To make this form functional, sign up at-->
                        <!-- https://startbootstrap.com/solution/contact-forms-->
                        
                        
                        
                        <!-- FORMULARIO-->
                        <form class="row g-3" method = "POST" action = "SvAgregarLibro"  enctype="multipart/form-data">
                            <div class="col-md-4">
                                <label for="validationServer01" class="form-label">Titulo del libro</label>
                                <input type="text" class="form-control" id="validationServer01" name = "titulo"required>
                                <div class="valid-feedback">
                                    Looks good!
                                </div>
                            </div>
                            <div class="col-md-4">
                                <label for="validationServer02" class="form-label">Autor</label>
                                <input type="text" class="form-control " id="validationServer02"  name = "autor" required>
                                <div class="valid-feedback">
                                    Looks good!
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label for="validationServer03" class="form-label">AÒo de PublicaciÛn</label>
                                <div class="mb-3">
                                    
                                    <input type="date" class="form-control" id="editar-tarea-fecha" name="fecha">

     
                                    
                             <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Portada</span>
                     <input type="file" class="form-control" name="foto"  placeholder="url foto" aria-label="Username" aria-describedby="basic-addon1" required="true" accept=".jpg, .jpeg">
                   </div>
 
                            <div class="col-12">
                                <div class="form-check">
                                   
                                    <div id="invalidCheck3Feedback" class="invalid-feedback">
                                       
                                    </div>
                                </div>
                            </div>
                            <div class="col-12">
                                <button class="btn btn-primary" type="submit">Agregar</button>
                                ††</div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
        <!-- Footer-->
        
        <!-- Copyright Section-->
        <div class="copyright py-4 text-center text-white">
            <div <button><a href="index.jsp">Cerrar sesion</a> </button> </div>
        </div>
        <!-- Portfolio Modals-->
        <!-- Portfolio Modal 1-->
        <div class="portfolio-modal modal fade" id="portfolioModal1" tabindex="-1" aria-labelledby="portfolioModal1" aria-hidden="true">
            <div class="modal-dialog modal-xl">
                <div class="modal-content">
                    <div class="modal-header border-0"><button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button></div>
                    <div class="modal-body text-center pb-5">
                        <div class="container">
                            <div class="row justify-content-center">
                                <div class="col-lg-8">
                                    <!-- Portfolio Modal - Title-->
                                    <h2 class="portfolio-modal-title text-secondary text-uppercase mb-0">Log Cabin</h2>
                                    <!-- Icon Divider-->
                                    <div class="divider-custom">
                                        <div class="divider-custom-line"></div>
                                        <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                                        <div class="divider-custom-line"></div>
                                    </div>
                                    <!-- Portfolio Modal - Image-->
                                    <img class="img-fluid rounded mb-5" src="assets/img/portfolio/cabin.png" alt="..." />
                                    <!-- Portfolio Modal - Text-->
                                    <p class="mb-4">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Mollitia neque assumenda ipsam nihil, molestias magnam, recusandae quos quis inventore quisquam velit asperiores, vitae? Reprehenderit soluta, eos quod consequuntur itaque. Nam.</p>
                                    <button class="btn btn-primary" data-bs-dismiss="modal">
                                        <i class="fas fa-xmark fa-fw"></i>
                                        Close Window
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- modal donde se muestran los libros disponibles-->

    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true"> 
         <div class="modal-dialog"> 
             <div class="modal-content"> 
                 <div class="modal-header"> 
                    <h5 class="modal-title" id="exampleModalLabel">Detalles del Libro</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button> 
                 </div>
                 <div class="modal-body"> 
                  
                     <div id="libro-details"> 
                         <!-- Aqu√≠ se a√±ade los detalles del perro-->
                </div>
                 </div> 
                 <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button> 
                </div>
             </div> 
         </div> 
     </div>
        
        
         <!-- modal donde se muestran los libros prestados-->

    <div class="modal fade" id="ModalPrestamo" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true"> 
         <div class="modal-dialog"> 
             <div class="modal-content"> 
                 <div class="modal-header"> 
                    <h5 class="modal-title" id="exampleModalLabel">Detalles del Libro</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button> 
                 </div>
                 <div class="modal-body"> 
                  
                     <div id="libroPres-details"> 
                         <!-- Aqu√≠ se a√±ade los detalles del perro-->
                </div>
                 </div> 
                 <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button> 
                </div>
             </div> 
         </div> 
     </div>
        
        
                <!-- Modal de confirmacion de la accion eliminar  -->
           
                
       
       
                
    <div class="modal fade" id="eliminarModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
     aria-labelledby="eliminarLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-body">
                <h2 align="center">øEst·s seguro de que deseas eliminar esta tarea?</h2>
                <form action="SvEliminar" method="POST">
                    <!-- Agregar un campo de entrada para mostrar el ID de la tarea -->
                    <input type="text" id="inputEliminar" name="inputEliminar" hidden>
            
                    </div>
                    <div class="modal-footer justify-content-center">
                        <button type="button" class="btn btn-primary" data-bs-dismiss="modal" style="margin-right: 10px; background-color: #512da8">Cancelar
                        </button>
                        <button type="submit" class="btn btn-danger" >Eliminar</button>
                </form>
            </div>
        </div>
    </div>
</div> 
       

        
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->   
        <script src="js/scripts.js"></script>
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <!-- * *                               SB Forms JS                               * *-->
        <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
        
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        
        <script>
    // funcion para mostrar los datos en la ventana modal
  $('#exampleModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget); // Bot√≥n que desencaden√≥ el evento
    var titulo = button.data('titulo'); // Obt√©n el nombre del perro

    // Realiza una solicitud AJAX al servlet para obtener los detalles del perro por su nombre
    $.ajax({
      url: 'SvAgregarLibro?titulo=' + titulo, // Cambia 'id' por el nombre del par√°metro que esperas en tu servlet
      method: 'GET',
      success: function (data) {
        // Actualiza el contenido del modal con los detalles del perro
        $('#libro-details').html(data);
      },
      error: function () {
        // Maneja errores aqu√≠ si es necesario y se imprime en consola
        console.log('Error al cargar los detalles del libro.');
      }
    });
  });
  
</script>
<script>
    // funcion para mostrar los datos en la ventana modal
  $('#ModalPrestamo').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget); // Bot√≥n que desencaden√≥ el evento
    var titulo= button.data('titulo'); // Obt√©n el nombre del 

    // Realiza una solicitud AJAX al servlet para obtener los detalles del perro por su nombre
    $.ajax({
      url: 'SvAgregarLibro?titulo=' + titulo, // Cambia 'id' por el nombre del par√°metro que esperas en tu servlet
      method: 'GET',
      success: function (data) {
        // Actualiza el contenido del modal con los detalles del perro
        $('#libroPres-details').html(data);
      },
      error: function () {
        // Maneja errores aqu√≠ si es necesario y se imprime en consola
        console.log('Error al cargar los detalles del libro.');
      }
    });
  });
  
</script>





<script>
    
    var id = "";
    $('#eliminarModal').on('show.bs.modal', function (event) {
        
        var button = $(event.relatedTarget); // BotÛn que activÛ el modal
        
        var tiEliminar = button.data('eliminar'); // ObtÈn el valor de la variable desde el botÛn
        
           $('#inputEliminar').val(tiEliminar);
        
        var modal = $(this);
        console.log(tiEliminar);
         id = tiEliminar;
        
    });
</script>


    </body>
    
    
    
</html>
