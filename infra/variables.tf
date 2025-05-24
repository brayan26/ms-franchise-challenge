variable "do_token" {
  description = "DigitalOcean API Token"
  type        = string
  sensitive   = true
}

variable "region" {
  default     = "nyc1"
  description = "Región del clúster"
}

variable "cluster_name" {
  default     = "nequi-cluster"
  description = "Nombre del clúster"
}

variable "k8s_version" {
  default     = "1.32.2-do.1"
  description = "Versión de Kubernetes"
}

variable "node_size" {
  default     = "s-2vcpu-4gb"
  description = "Tamaño de cada nodo"
}

variable "node_count" {
  default     = 2
  description = "Cantidad inicial de nodos"
}
