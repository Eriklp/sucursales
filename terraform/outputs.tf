output "db_endpoint" {
  description = "The endpoint of the MySQL database"
  value       = aws_db_instance.franquicias_db.endpoint
}

output "db_name" {
  description = "The name of the MySQL database"
  value       = aws_db_instance.franquicias_db.db_name
}

output "db_password" {
  description = "Password of database"
  value = aws_db_instance.franquicias_db.password
  sensitive = true
}